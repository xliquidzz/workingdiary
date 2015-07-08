/**
 * This file is part of Working Diary.
 *
 * Working Diary is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Working Diary is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Working Diary.  If not, see <http://www.gnu.org/licenses/>.
 */

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
