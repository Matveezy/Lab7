package lib.collectionworker;

import client.user.User;
import lib.collection.*;
import server.database.DataBaseManager;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class CollectionManager {

    private LinkedList<Dragon> dragons;
    private DragonFactory dragonFactory;
    private LocalDate creationCollectionDate;
    private final Lock reentrantLock = new ReentrantLock();

    public CollectionManager() {
        creationCollectionDate = LocalDate.now();
        dragons = new LinkedList<Dragon>();
        dragonFactory = new DragonFactory();
    }


    /**
     * Методы , реализующие команды пользователя
     */

    public void insert(Dragon dragon) {
        lock();
        dragons.add(dragon);
        reentrantLock.unlock();
    }


    public boolean removeById(long id) {
        lock();
        if (id < 1 && id > dragons.size()) {
            reentrantLock.unlock();
            return false;
        }
        if (DataBaseManager.removeById(id)) {
            dragons.remove(getElementById(id));
            reentrantLock.unlock();
            return true;
        }
        reentrantLock.unlock();
        return false;
    }


    public String clear(User user) {
        lock();
        List<Dragon> loggedUserDragons = getLoggedUserDragons(user, dragons);
        if (loggedUserDragons.size() == 0) {
            reentrantLock.unlock();
            return "У данного пользователя нет добавленных объектов!";
        }
        if (DataBaseManager.clear(user.getUsername())) {
            dragons.removeAll(loggedUserDragons);
            reentrantLock.unlock();
            return "Объекты удалены!";
        }
        reentrantLock.unlock();
        return "Ошибка с базой данных!";
    }

    /**
     * Вспомогательные методы(геттеры и сеттеры)
     *
     * @param list
     */

    public void setDragons(LinkedList<Dragon> list) {
        dragons = list;
    }

    public DragonFactory getDragonFactory() {
        return dragonFactory;
    }

    public int getSize() {
        return dragons.size();
    }

    public LinkedList<Dragon> getDragons() {
        return dragons;
    }

    public void setDragonFactory(DragonFactory dragonFactory) {
        this.dragonFactory = dragonFactory;
    }

    public LocalDate getCreationCollectionDate() {
        return creationCollectionDate;
    }


    /**
     * Вспомогательные методы с более сложной логикой
     */

    public List<Dragon> getLoggedUserDragons(User user, LinkedList<Dragon> allDragons) {
        lock();
        List<Dragon> res = allDragons.stream()
                .filter(dragon -> dragon.getUsername().equals(user.getUsername()))
                .collect(Collectors.toList());
        reentrantLock.unlock();
        return res;
    }


    public Dragon findMinDragon(User user) {
        lock();
        Dragon minDragon;
        Optional<Dragon> maybeMinDragon = dragons.stream()
                .filter(dragon -> dragon.getUsername().equals(user.getUsername()))
                .min(Dragon::compareTo)
                .stream().findFirst();
        if (maybeMinDragon.isPresent()) {
            minDragon = maybeMinDragon.get();
        } else minDragon = null;
        reentrantLock.unlock();
        return minDragon;
    }

    public LinkedList<Dragon> reverseCollection() {
        LinkedList<Dragon> newDragons = new LinkedList<>();
        int count = 0;
        Iterator<Dragon> iterator = dragons.descendingIterator();
        while (iterator.hasNext() && count < dragons.size()) {
            newDragons.add(iterator.next());
            count++;
        }
        return newDragons;
    }

    public boolean isElementInCollection(long id, User user) {
        lock();
        if (id < 1) {
            reentrantLock.unlock();
            return false;
        }
        boolean result = dragons.stream()
                .filter(dragon -> dragon.getUsername().equals(user.getUsername()))
                .anyMatch(val -> val.getId() == id);
        reentrantLock.unlock();
        return result;
    }

    public Dragon getElementById(long id) {
        lock();
        if (id < 1) {
            Dragon dragon = new Dragon();
            dragon.setId(-1);
            reentrantLock.unlock();
            return dragon;
        }
        Dragon result = null;
        Optional<Dragon> maybeDragon = dragons.stream()
                .filter(dragon -> dragon.getId() == id)
                .findFirst();
        if (maybeDragon.isPresent()) {
            result = maybeDragon.get();
        }
        reentrantLock.unlock();
        return result;
    }

    private void lock() {
        while (true) {
            if (reentrantLock.tryLock()) {
                return;
            }
        }
    }
}
