//
//   Copyright 2016  Cityzen Data
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
//

package io.warp10.script.binary;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import io.warp10.script.NamedWarpScriptFunction;
import io.warp10.script.WarpScriptStackFunction;
import io.warp10.script.WarpScriptException;
import io.warp10.script.WarpScriptStack;

/**
 * Subtracts the two operands on top of the stack
 */
public class SUB extends NamedWarpScriptFunction implements WarpScriptStackFunction {

  public SUB(String name) {
    super(name);
  }
  
  @Override
  public Object apply(WarpScriptStack stack) throws WarpScriptException {
    Object op2 = stack.pop();
    Object op1 = stack.pop();
    
    if (op2 instanceof Number && op1 instanceof Number) {
      if (op1 instanceof Double || op2 instanceof Double) {
        stack.push(((Number) op1).doubleValue() - ((Number) op2).doubleValue());
      } else {
        stack.push(((Number) op1).longValue() - ((Number) op2).longValue());        
      }
    } else if (op1 instanceof RealMatrix && op2 instanceof RealMatrix) {
      stack.push(((RealMatrix) op1).subtract((RealMatrix) op2));
    } else if (op1 instanceof RealVector && op2 instanceof RealVector) {
      stack.push(((RealVector) op1).subtract((RealVector) op2));
    } else {
      throw new WarpScriptException(getName() + " can only operate on numeric values, vectors and matrices.");
    }
    
    return stack;
  }
}
