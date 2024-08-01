package org.raddan.newspaper.config.fetcher;

import java.util.Set;

/**
 * @author Alexander Dudkin
 */
public interface Fetcher {
    Set<?> fetch(String param);
}
