package com.tw4j.hydrogen;

import com.tw4j.hydrogen.source.DataPump;

public class Main {

  private DataPump pump;
  
  public static void main(String[] args) throws Exception {
    Main main =new Main();
    
    main.pump = new DataPump();
    main.pump.pump();
  }

}
