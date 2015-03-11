package ch.css.workingdiary.util;

/**
 * Created by sandro on 11.03.2015.
 */
public enum Role {
    APPRENTICE(1), TRAINER(2), VOCATION_TRAINER(3);

    private final long roleId;

    Role(final long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }
}
