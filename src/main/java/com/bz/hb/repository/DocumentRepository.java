package com.bz.hb.repository;

/**
 * User: sohel
 * Date: 05/12/20
 * Time: 12:46 AM
 */
import com.bz.hb.model.doc.Document;
import com.bz.hb.model.doc.DocumentSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
Optional<Document>findByDocumentSourceAndObjectRefIdAndActiveStatus(DocumentSource documentSource, Long objectRefId, Integer activeStatus);
}
