package org.walkmod.sonar.visitors;

import org.junit.Assert;
import org.junit.Test;
import org.walkmod.javalang.ast.CompilationUnit;
import org.walkmod.javalang.test.SemanticTest;


public class RemoveUselessImportsTest extends SemanticTest {

   @Test
   public void testRemoval() throws Exception{
      CompilationUnit cu = compile("import java.util.List; public class Foo{}");
      RemoveUselessImports visitor = new RemoveUselessImports();
      cu.accept(visitor, null);
      Assert.assertTrue(cu.getImports().isEmpty());
      
      System.out.println(cu.toString());
   }
   
   @Test
   public void testIsNotRemoved() throws Exception{
      CompilationUnit cu = compile("import java.util.List; public class Foo{ private List list; }");
      RemoveUselessImports visitor = new RemoveUselessImports();
      cu.accept(visitor, null);
      Assert.assertFalse(cu.getImports().isEmpty());
      
      System.out.println(cu.toString());
   }
}
