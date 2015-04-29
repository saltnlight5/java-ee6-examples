/*
 *  Zemian Deng 2014
 */

package zemian.sakila;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author zedeng
 */
@Embeddable
public class FilmActorPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "actor_id")
    private short actorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "film_id")
    private short filmId;

    public FilmActorPK() {
    }

    public FilmActorPK(short actorId, short filmId) {
        this.actorId = actorId;
        this.filmId = filmId;
    }

    public short getActorId() {
        return actorId;
    }

    public void setActorId(short actorId) {
        this.actorId = actorId;
    }

    public short getFilmId() {
        return filmId;
    }

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) actorId;
        hash += (int) filmId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FilmActorPK)) {
            return false;
        }
        FilmActorPK other = (FilmActorPK) object;
        if (this.actorId != other.actorId) {
            return false;
        }
        if (this.filmId != other.filmId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zemian.sakila.FilmActorPK[ actorId=" + actorId + ", filmId=" + filmId + " ]";
    }

}
