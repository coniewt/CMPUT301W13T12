package ca.ualberta.c301w13t12recipes.model;

import java.util.Collection;

/**
 * Total hits that match the query
 * @author shihao1
 *
 * @param <T>
 */
public class Hits<T> {
    int total;
    double max_score;
    Collection<ElasticSearchResponse<T>> hits;
    
    /**
     * Get total hits that match the query
     * @return Collection<ElasticSearchResponse<T>>
     */
    public Collection<ElasticSearchResponse<T>> getHits() {
        return hits;
    }
    
    /**
     * Convert the search response header to string
     */
    public String toString() {
        return (super.toString()+","+total+","+max_score+","+hits);
    }
}