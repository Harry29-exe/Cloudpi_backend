package com.cloudpi.cloudpi_backend.files;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
public class FileAuthorityVerifier {

    //TODO
    public boolean isOwner(String username, Long discObjectId) {
        throw new NotImplementedException();
    }

    //TODO
    public boolean canRead(String username, Long discObjectId) {
        throw new NotImplementedException();
    }

    //TODO
    public boolean canWrite(String username, Long discObjectId) {
        throw new NotImplementedException();
    }

    //TODO
    public boolean canShare(String username, Long discObjectId) {
        throw new NotImplementedException();
    }
}