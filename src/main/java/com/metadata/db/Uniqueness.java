package com.metadata.db;

public @interface Uniqueness {
    Constraints constraints()default @Constraints(unique = true);

}
