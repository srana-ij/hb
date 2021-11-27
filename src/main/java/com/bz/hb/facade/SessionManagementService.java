package com.bz.hb.facade;

/**
 * created by srana on 07/10/20 at 10.45 AM
 */

import com.bz.hb.facade.data.AuthenticatedUser;

public interface SessionManagementService {
    AuthenticatedUser getAuthenticatedUser();
}
