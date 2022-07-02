package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        int changed = 1;
        int deleted = 1;

        Map<Integer, String> mapa = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User element : previous) {
            if (mapa.containsKey(element.getId())
            && !element.getName().equals(mapa.get(element.getId()))) {
                rsl.setChanged(changed++);
                mapa.remove(element.getId());
                continue;
            }
            if (!mapa.containsKey(element.getId())) {
                rsl.setDeleted(deleted++);
                continue;
            }
            mapa.remove(element.getId(), element.getName());
        }
        rsl.setAdded(mapa.size());
        return rsl;
    }
}