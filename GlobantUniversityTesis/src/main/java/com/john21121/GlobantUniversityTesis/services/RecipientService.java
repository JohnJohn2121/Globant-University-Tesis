package com.john21121.GlobantUniversityTesis.services;


import com.john21121.GlobantUniversityTesis.mailingsystem.Recipient;

import java.util.Set;

public interface RecipientService {
    Recipient findById(Long id);

    Recipient createNewRecipient(Recipient recipient);

    Set<Recipient> getMessagesInRecipient() ;

    void deleteById(Long deletionId);
}
