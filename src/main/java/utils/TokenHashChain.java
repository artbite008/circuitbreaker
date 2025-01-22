package utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class TokenHashChain extends LinkedHashMap<String,Node> {
    private static final int CHAIN_MAX_SIZE = 100;
    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Node> oldest){
        return size() > CHAIN_MAX_SIZE;
    }
}

class Node{}