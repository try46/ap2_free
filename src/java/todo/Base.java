/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author try
 */
public class Base {
  public static void main(String[] args) {
     String source = "root";
        //エンコード前にバイト配列に置き換える際のCharset
        Charset charset = StandardCharsets.UTF_8;

        //エンコード処理
        String result = Base64.getEncoder().encodeToString(source.getBytes(charset));
        //標準出力
        System.out.println("エンコード結果;"+ result);
  }
}
