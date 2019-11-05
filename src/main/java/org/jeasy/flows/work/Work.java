/**
 * The MIT License
 *
 *  Copyright (c) 2017, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package org.jeasy.flows.work;

import javax.script.Bindings;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Implementations of this interface must:
 * <ul>
 *     <li>catch exceptions and return {@link WorkStatus#FAILED}</li>
 *     <li>make sure the work in finished in a finite amount of time</li>
 * </ul>
 *
 * Work name must be unique within a workflow.
 */
public interface Work extends Callable<WorkReport> {

    String getName();

    WorkReport call();

    Bindings runContext();

    void runContext(Bindings contextForCall);

    Bindings EMPTY_BINDINGS = new Bindings() {

        @Override
        public Object put(String name, Object value) {
            return null;
        }

        @Override
        public void putAll(Map<? extends String, ?> toMerge) {

        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public Object get(Object key) {
            return null;
        }

        @Override
        public Object remove(Object key) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Set<String> keySet() {
            return Collections.emptySet();
        }

        @Override
        public Collection<Object> values() {
            return Collections.emptyList();
        }

        @Override
        public Set<Entry<String, Object>> entrySet() {
            return Collections.emptySet();
        }
    };

    interface NonContextualWork extends Work{
        default  Bindings runContext(){
           return EMPTY_BINDINGS;
        }
        default void runContext( Bindings contextForCall){

        }
    }
}
