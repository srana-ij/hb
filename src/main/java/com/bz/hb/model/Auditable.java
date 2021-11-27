package com.bz.hb.model;

/**
 * created by srana on 07/10/20 at 10.33 AM
 */

import java.time.LocalDateTime;

public interface Auditable {
    LocalDateTime getCreatedAt();

    void setCreatedAt(LocalDateTime dateTime);

    LocalDateTime getModifiedAt();

    void setModifiedAt(LocalDateTime dateTime);
}
