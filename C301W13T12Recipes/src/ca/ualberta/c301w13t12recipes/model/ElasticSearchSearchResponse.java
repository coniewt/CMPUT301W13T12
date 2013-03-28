package ca.ualberta.c301w13t12recipes.model;

import java.util.ArrayList;
import java.util.Collection;



/**
 * @author dw
 *
 * @param <T>
 */
public class ElasticSearchSearchResponse<T> {
    int took;
    boolean timed_out;
    transient Object _shards;
    Hits<T> hits;
    boolean exists;    
    public Collection<ElasticSearchResponse<T>> getHits() {
        return hits.getHits();        
    }
    /**
     * @return collection<T>
     */
    public Collection<T> getSources() {
        Collection<T> out = new ArrayList<T>();
        for (ElasticSearchResponse<T> essrt : getHits()) {
            out.add( essrt.getSource() );
        }
        return out;
    }
    @Override
    public String toString() {
        return (super.toString() + ":" + took + "," + _shards + "," + exists + ","  + hits);     
    }
}
