package ca.ualberta.c301w13t12recipes.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Handle response coming from a Elastic Search result
 * @param T
 */
public class ElasticSearchSearchResponse<T> {
    int took;
    boolean timed_out;
    transient Object _shards;
    Hits<T> hits;
    boolean exists;    
    
    /**
     * Get total his that match the querry
     * @return Collection<ElasticSearchResponse<T>>
     */
    public Collection<ElasticSearchResponse<T>> getHits() {
        return hits.getHits();        
    }
    
    /**
     * Get resources from each hits
     * @return Collection<T>
     */
    public Collection<T> getSources() {
        Collection<T> out = new ArrayList<T>();
        for (ElasticSearchResponse<T> essrt : getHits()) {
            out.add( essrt.getSource() );
        }
        return out;
    }
    
    /**
     * Convert the header of the return from elastic search to string
     */
    public String toString() {
        return (super.toString() + ":" + took + "," + _shards + "," + exists + ","  + hits);     
    }
}
