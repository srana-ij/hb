package com.bz.hb.service.impl;

import com.bz.hb.model.doc.Document;
import com.bz.hb.model.doc.DocumentSource;
import com.bz.hb.repository.DocumentRepository;
import com.bz.hb.service.DocumentService;
import com.bz.hb.util.Constants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * User: sohel
 * Date: 05/12/20
 * Time: 12:46 AM
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class DocumentServiceImpl implements DocumentService {


    @NonNull private final DocumentRepository documentRepository;

    @Override
    public Optional<Document> findDocumentById(Long documentId) {
        return documentRepository.findById(documentId);
    }

    @Override
    public List<Document> saveDocuments(List<Document> documents) {
        return documentRepository.saveAll(documents);
    }

    @Override
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Optional<Document> findDocumentBySourceAndObject(DocumentSource documentSource, Long objectRefId) {
        return documentRepository.findByDocumentSourceAndObjectRefIdAndActiveStatus(documentSource,objectRefId, Constants.ACTIVE_STATUS);
    }
}
