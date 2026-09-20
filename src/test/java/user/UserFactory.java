package user;

import utills.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withLockedPermission() {
        return new User(PropertyReader.getProperty("saucedemo.locked.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withEmptyUser() {
        return new User(PropertyReader.getProperty("saucedemo.without.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withEmptyPassword() {
        return new User(PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.without.password"));
    }

    public static User withRegistrUser() {
        return new User(PropertyReader.getProperty("saucedemo.registr.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }
}
