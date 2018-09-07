package Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.transaction.Transactional;

import ninja.jpa.UnitOfWork;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity(name="medicines")
public class Med {

    @Id
    @Column(name="id")    
    private String id;

    @Column(name="mid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String mid;
    
    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Contact contact;

	@Column(name="mname")
    private String mname;    

    @Column(name="counts")
    private int counts;

    public Med() {}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMid() {
        return mid;
    }
    public void setMid(String mid) {
        this.mid = mid;
    }
    public String getMname() {
        return mname;
    }
    public void setMname(String mname) {
        this.mname = mname;
    }
    public int getCounts() {
        return counts;
    }
    public void setCounts(int counts) {
        this.counts = counts;
    }
}  