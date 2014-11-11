/*
  Copyright (c) 2002, 2014, Oracle and/or its affiliates. All rights reserved.

  The MySQL Connector/J is licensed under the terms of the GPLv2
  <http://www.gnu.org/licenses/old-licenses/gpl-2.0.html>, like most MySQL Connectors.
  There are special exceptions to the terms and conditions of the GPLv2 as it is applied to
  this software, see the FLOSS License Exception
  <http://www.mysql.com/about/legal/licensing/foss-exception.html>.

  This program is free software; you can redistribute it and/or modify it under the terms
  of the GNU General Public License as published by the Free Software Foundation; version 2
  of the License.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this
  program; if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth
  Floor, Boston, MA 02110-1301  USA

 */

package com.mysql.jdbc;

import java.sql.SQLException;

/**
 * Thrown when a result sate is not updatable
 */
public class NotUpdatable extends SQLException {

    private static final long serialVersionUID = 8084742846039782258L;

    /**
     * The message to use when result set is not updatable.
     * 
     * The same message is used in the warnings generated by Updatabale result
     * set.
     */
    public static final String NOT_UPDATEABLE_MESSAGE = Messages.getString("NotUpdatable.0") + Messages.getString("NotUpdatable.1")
            + Messages.getString("NotUpdatable.2") + Messages.getString("NotUpdatable.3") + Messages.getString("NotUpdatable.4")
            + Messages.getString("NotUpdatable.5");

    /**
     * Creates a new NotUpdatable exception.
     */
    public NotUpdatable() {
        this(NOT_UPDATEABLE_MESSAGE);
    }

    /**
     * Append the given reason to the not updatable message if the reason is not
     * null.
     */
    public NotUpdatable(String reason) {
        super(reason + Messages.getString("NotUpdatable.1") + Messages.getString("NotUpdatable.2") + Messages.getString("NotUpdatable.3")
                + Messages.getString("NotUpdatable.4") + Messages.getString("NotUpdatable.5"), SQLError.SQL_STATE_GENERAL_ERROR);
    }
}