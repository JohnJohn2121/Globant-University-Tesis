package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import com.john21121.GlobantUniversityTesis.mailingsystem.Recipient;
import com.john21121.GlobantUniversityTesis.repository.RecipientRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipientServiceImpl implements RecipientService {

    private final RecipientRepository recipientRepository;

    public RecipientServiceImpl(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    @Override
    public Recipient findById(Long id) {
        Optional<Recipient> recipientOptional = recipientRepository.findById(id);
        if (!recipientOptional.isPresent()){
            throw new NotFoundException("This User Recipient does not exist");
        }
        return recipientOptional.get();
    }

    @Override
    public Recipient createNewRecipient(Recipient recipient) {
        recipientRepository.save(recipient);
        return recipient;
    }

    @Override
    public Set<Recipient> getMessagesInRecipient() {
        Set<Recipient> recipients = new HashSet<>();
        recipientRepository.findAll().iterator().forEachRemaining(recipients::add);
        return recipients;
    }

    @Override
    public void deleteById(Long deletionId) {
        recipientRepository.deleteById(deletionId);
    }
}
