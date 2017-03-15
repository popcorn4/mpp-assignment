package util;

import domain.BaseEntity;

/**
 * Created by Nicu on 3/11/2017.
 */
public class XmlWriter<ID, T extends BaseEntity<ID>> {

    private String fileName;

    public XmlWriter(String fileName) {
        this.fileName = fileName;
    }

    public void save(T entity) {
        //TODO implement writer
    }


}

