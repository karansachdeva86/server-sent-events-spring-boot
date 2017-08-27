package com.hexor.reandroid.persistence.entity;

import javax.persistence.*;

@Cacheable
//@Entity
@Table(name="customer")
public class CustomerEntity {

    private String  id;
    private String  name;
    private String  password;
    private boolean enabled;
    private boolean decryptionRequired;

    @Id
   // @GeneratedValue
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Basic
    @Column(name="name",unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Basic
    @Column(name="is_decryption_required")
    public boolean isDecryptionRequired() {
        return decryptionRequired;
    }

    public void setDecryptionRequired(boolean decryptionRequired) {
        this.decryptionRequired = decryptionRequired;
    }

    @Basic
    @Column(name="is_enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerEntity)) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

}
