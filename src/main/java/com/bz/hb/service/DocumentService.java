package com.bz.hb.service;


import com.bz.hb.model.doc.Document;
import com.bz.hb.model.doc.DocumentSource;

import java.util.List;
import java.util.Optional;

/**
 * User: sohel
 * Date: 05/12/20
 * Time: 12:46 AM
 */
public interface DocumentService {
    Optional<Document> findDocumentById(Long documentId);
    List<Document> saveDocuments(List<Document> documents);
    Document saveDocument(Document document);
    Optional<Document> findDocumentBySourceAndObject(DocumentSource documentSource, Long objectRefId);
}
