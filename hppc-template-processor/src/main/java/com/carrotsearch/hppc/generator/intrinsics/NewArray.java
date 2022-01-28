/*
 * HPPC
 *
 * Copyright (C) 2010-2022 Carrot Search s.c.
 * All rights reserved.
 *
 * Refer to the full license file "LICENSE.txt":
 * https://github.com/carrotsearch/hppc/blob/master/LICENSE.txt
 */
package com.carrotsearch.hppc.generator.intrinsics;

import com.carrotsearch.hppc.generator.TemplateOptions;
import com.carrotsearch.hppc.generator.Type;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class NewArray extends AbstractIntrinsicMethod {
  @Override
  public void invoke(
      Matcher m,
      StringBuilder sb,
      TemplateOptions templateOptions,
      String genericCast,
      ArrayList<String> params) {
    expectArgumentCount(m, params, 1);

    genericCast = inferTemplateCastName(m, templateOptions, genericCast);
    Type type = inferTemplateType(m, templateOptions, genericCast);

    switch (type) {
      case GENERIC:
        sb.append(format("((%s[]) new Object [%s])", genericCast, params.get(0)));
        break;

      case BYTE:
      case CHAR:
      case DOUBLE:
      case FLOAT:
      case INT:
      case LONG:
      case SHORT:
        sb.append(format("(new %s [%s])", type.getType(), params.get(0)));
        break;

      default:
        throw unreachable();
    }
  }
}
