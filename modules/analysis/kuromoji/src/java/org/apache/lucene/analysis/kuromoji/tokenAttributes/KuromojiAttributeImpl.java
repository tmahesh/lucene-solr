package org.apache.lucene.analysis.kuromoji.tokenAttributes;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.lucene.analysis.kuromoji.Token;
import org.apache.lucene.util.AttributeImpl;
import org.apache.lucene.util.AttributeReflector;

// TODO: we need to cache lazy state of POS/basicForm/reading/etc here (and implement all those attributes)
// so that we don't do the (currently expensive) decoding of the metadata multiple times.
public class KuromojiAttributeImpl extends AttributeImpl implements BasicFormAttribute, Cloneable {
  private Token token;
  
  public String getBasicForm() {
    return token == null ? null : token.getBasicForm();
  }
  
  public void setToken(Token token) {
    this.token = token;
  }

  @Override
  public void clear() {
    token = null;
  }

  @Override
  public void copyTo(AttributeImpl target) {
    BasicFormAttribute t = (BasicFormAttribute) target;
    t.setToken(token);
  }
  
  @Override
  public void reflectWith(AttributeReflector reflector) {
    reflector.reflect(BasicFormAttribute.class, "basicForm", getBasicForm());
  }
}