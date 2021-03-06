package ui;
import model.*;
import java.util.*;

/**
*This class handless user interaction via a menu with different options that represent a functionality<br>
*of the app. Implements the UIs interface.<br>
*@author Samuel Hernandez / Zac.<br>
*@since 0.1 (Then called <i>MainMenu</i>, renamed to <i>Menus</i> in version 0.3)<br>
*@see UIs UIs Interface<br>
*/
public class Menus implements UIs {

  private final int ADD_USER = 1;
  private final int SHOW_USERS = 2;
  private final int ADD_SONG = 3;
  private final int SHOW_POOL = 4;
  private final int ADD_PLAYLIST = 5;
  private final int SHOW_PLAYLISTS = 6;
  private boolean exit = false;
  private App msc = new App();

  /**
  *Displays the welcome logo on screen, using the app logo as specified in the UIs class. <br>
  *<b>Pre: </b><br>
  *<b>Post: </b>The menu is displayed.<br>
  *@param millis Integer that describes the amount of milliseconds the console will wait per line. <b>Must be of type <i>int</i>.</b><br>
  *@param in Scanner object that receives user input. <b>Must be an already initialized <i>Scanner</i> object.</b><br>
  */
  private void welcomeLogo(int millis, Scanner in) {
    clear();
    System.out.println("(Version " + Main.VERSION + ")");
    slowPrint(millis,LOGO);
    System.out.println("(Presionar [ENTER] para continuar...)");
    in.nextLine();
    System.out.println("Iniciando...");
    wait(1000);
    clear();
  }

  /**
  *Starts the program.<br>
  *<b>Pre: </b><br>
  *<b>Post: </b>The program is started succesfully.
  *@param millis Integer that describes the amount of milliseconds the console will wait per line. <b>Must be of type <i>int</i>.</b><br>
  */
  public void startProgram(int millis) {
    Scanner in = new Scanner(System.in);
    welcomeLogo(5,in);
    do {
      showMenu(millis,in);
    }while (!exit);
  }

  /**
  *Displays the main menu, using the string array declared in the UIs interface.<br>
  *<b>Pre: </b><br>
  *<b>Post: </b>The menu is displayed.<br>
  *@param millis Integer that describes the amount of milliseconds the console will wait per line. <b>Must be of type <i>int</i>.</b><br>
  *@param in Scanner object that receives user input. <b>Must be an already initialized <i>Scanner</i> object.</b><br>
  */
  private void showMenu(int millis, Scanner in) {
    clear();
    slowPrint(millis,MAIN_MENU);
    switchMainMenu(in.nextInt(),in,millis);
  }

  /**
  *Decides on which method to execute according to the user input given in showMenu.<br>
  *<b>Pre: </b><br>
  *<b>Post: </b>The correct menu is called and showed.<br>
  *@param answer Integer that handles the decision of the user.<br>
  *@param  in Scanner object that receives user input. <b>Must be an already initialized <i>Scanner</i> object.</b><br>
  *@param  millis Integer that describes the amount of milliseconds the console will wait per line. <b>Must be of type <i>int</i>.</b><br>
  *@see #showMenu(int,Scanner) showMenu<br>
  */
  private void switchMainMenu(int answer, Scanner in, int millis) {
    switch (answer) {
      case 0:
        exit = true;
        break;
      case ADD_USER:
        showAddUserMenu(millis,in);
        break;
      case SHOW_USERS:
        showProfilesMenu(millis,in);
        break;
      case ADD_SONG:
        showAddSongMenu(millis,in);
        break;
      case SHOW_POOL:
        showPoolMenu(millis,in);
        break;
      case ADD_PLAYLIST:
        showAddPlaylistsMenu(millis,in);
        break;
      case SHOW_PLAYLISTS:
        showPlaylistsMenu(millis,in);
        break;
      default:
        throw new IllegalStateException("Valor inesperado: " + answer);
    }
  }

  /**
  *Called when the first option is selected in showMenu, allowing the user to add a new user to the app.<br>
  *<b>Pre: </b> <br>
  *<b>Post: </b>The menu is displayed.<br>
  *@param millis Integer that describes the amount of milliseconds the console will wait per line. <b>Must be of type <i>int</i>.</b><br>
  *@param in Scanner object that receives user input. <b>Must be an already initialized <i>Scanner</i> object.</b><br>
  *@see #showMenu(int,Scanner) showMenu method
  */
  private void showAddUserMenu(int millis, Scanner in) {
    clear();
    String[] newUserInfo = new String[3];
    System.out.println("************************************************");
    wait(millis);
    System.out.println("******************Nuevo usuario*****************");
    wait(millis);
    System.out.println("*Nombre de usuario:                            *\n");
    newUserInfo[0] = in.next();
    in.nextLine();
    System.out.println("*Contrasenia:                                  *\n");
    newUserInfo[1] = in.nextLine();
    System.out.println("*Edad:                                         *\n");
    newUserInfo[2] = in.nextLine();
    msc.addToUserList(newUserInfo);
    slowPrint(millis, new String[] {
      "************************************************",
      "***********Usuario creado con exito!************",
      "************************************************"});
    wait(1492);
  }

  /**
  *Called when the second option is selected in showMenu, allowing the app to display every user in<br>
  *it with its current atributes.<br>
  *<b>Pre: </b><br>
  *<b>Post: </b>The menu is displayed.<br>
  *@param millis Integer that describes the amount of milliseconds the console will wait per line. <b>Must be of type <i>int</i>.</b><br>
  *@param in Scanner object that receives user input. <b>Must be an already initialized <i>Scanner</i> object.</b><br>
  *@see #showMenu(int,Scanner) showMenu method
  */
  private void showProfilesMenu(int millis,Scanner in) {
    clear();
    System.out.println("************************************************");
    for (User u : msc.getUserList()) {
      slowPrint(millis,u.showInfo());
      }
    slowPrint(millis, new String[] {
      "************************************************",
      "*Volver                                 [ENTER]*",
      "************************************************"});
    in.nextLine();
    in.nextLine();
  }

  /**
  *Called when the third option is selected in showMenu, allowing the user to add a new song to the app.<br>
  *<b>Pre: </b> <br>
  *<b>Post: </b>The menu is displayed.<br>
  *@param millis Integer that describes the amount of milliseconds the console will wait per line. <b>Must be of type <i>int</i>.</b><br>
  *@param in Scanner object that receives user input. <b>Must be an already initialized <i>Scanner</i> object.</b><br>
  *@see #showMenu(int,Scanner) showMenu method
  */
  private void showAddSongMenu(int millis, Scanner in) {
    clear();
    String[] newSongInfo = new String[5];
    Duration newDurationObj = new Duration();
    in.nextLine();
    System.out.println("************************************************");
    wait(millis);
    System.out.println("*******************Nueva cancion****************");
    wait(millis);
    System.out.println("*Nombre de la cancion:                         *\n");
    newSongInfo[0] = in.nextLine();
    System.out.println("*Artista:                                      *\n");
    newSongInfo[1] = in.nextLine();
    System.out.println("*Album:                                        *\n");
    newSongInfo[2] = in.nextLine();
    System.out.println("*Fecha de lanzamiento [DD/MM/AAAA]:            *\n");
    newSongInfo[3] = in.nextLine();
    System.out.println("*Duracion [MM:SS]:                             *\n");
    newDurationObj.toIntFormat(in.nextLine());
    System.out.println("*********************Generos********************");
    wait(millis);

    int j = 0;
    for (Genre g : Genre.values()) {
      System.out.println("**[" + j + "] " + g );
      j++;
    }
    System.out.println("*Introduzca el numero correspondiente al genero*");
    wait(millis);
    System.out.println("*de la cancion:                                *\n");
    newSongInfo[4] = in.nextLine();
    System.out.println("*Escoger un usuario que aniadira la cancion:   *\n");

    int k = 1;
    for (User u : msc.getUserList()) {
      System.out.println("**[" + k + "] " + u.getUserName());
      k++;
    }
    k = in.nextInt() - 1;
    in.nextLine();
    if (msc.addToPool(newSongInfo,newDurationObj)) {
      msc.getUserList().get(k).modifyRank();
      slowPrint(millis, new String[] {
        "************************************************",
        "************Cancion creada con exito!***********",
        "************************************************"});
    }else {
      slowPrint(millis, new String[] {
        "************************************************",
        "**********Algo salio mal. Saliendo...***********",
        "************************************************"});
    }
    wait(1492);
  }

  /**
  *Called when the fourth option is selected in showMenu, allowing the app to display every song in<br>
  *it with its current atributes.<br>
  *<b>Pre: </b><br>
  *<b>Post: </b>The menu is displayed.<br>
  *@param millis Integer that describes the amount of milliseconds the console will wait per line. <b>Must be of type <i>int</i>.</b><br>
  *@param in Scanner object that receives user input. <b>Must be an already initialized <i>Scanner</i> object.</b><br>
  *@see #showMenu(int,Scanner) showMenu method
  */
  private void showPoolMenu(int millis, Scanner in) {
    clear();
    for (Song s : msc.getPool()) {
      slowPrint(millis, s.showInfo());
    }
    slowPrint(millis, new String[] {
      "************************************************",
      "*Aniadir a un playlist                      [1]*",
      "*Volver                                     [0]*",
      "************************************************"});
    int answer = in.nextInt();
    in.nextLine();

    if (answer == 1 && !msc.getPlaylists().isEmpty()) {
      addSongToPool(millis,in);
    } else if (answer == 1 && msc.getPlaylists().isEmpty()) {
      slowPrint(millis, new String[] {
        "****************No hay Playlists****************",
        "*************Intente de nuevo luego*************",
        "************************************************"});
      wait(1492);
    }
  }

  /**
  *Transitory method that adds an existing song in the shared pool to a specified playlist.<br>
  *<b>Pre: </b><br>
  *<b>Post: </b>The song is added to the playlist.<br>
  *@param millis Integer that describes the amount of milliseconds the console will wait per line. <b>Must be of type <i>int</i>.</b><br>
  *@param in Scanner object that receives user input. <b>Must be an already initialized <i>Scanner</i> object.</b><br>
  *@see Playlist#addSong(Song)<br>
  *@see App#addToPool(String[], Duration)
  */
  private void addSongToPool(int millis, Scanner in) {
    clear();
    int k = 1;
    System.out.println("**************Escoger una cancion***************");
    for (Song ss : msc.getPool()) {
      System.out.println("*[" + k + "] " + ss.getSongTitle());
      k++;
    }
    int newSongIndex = in.nextInt() - 1;
    in.nextLine();
    k = 1;
    System.out.println("*************Escoger una playlist***************");
    for (Playlist pl : msc.getPlaylists()) {
      System.out.println("**[" + k + "] " + pl.getPlaylistName() + " (" + pl.getSongs().size() + " canciones agregadas)");
    }
    int playlistIndex = in.nextInt() - 1;
    in.nextLine();
    boolean found = msc.getPlaylists().get(playlistIndex).addSong(msc.getPool().get(newSongIndex));
    if (!found) {
      slowPrint(millis, new String[] {
        "************************************************",
        "**********Cancion agregada con exito!***********",
        "************************************************"});
    }else {
      slowPrint(millis, new String[] {
        "************************************************",
        "***La cancion ya existe dentro de la playlist***",
        "*************Intente de nuevo luego*************",
        "************************************************"});
    }
    wait(1492);
  }

  /**
  *Called when the fifth option is selected in showMenu, allowing the user to add a playlist to the app.<br>
  *<b>Pre: </b> <br>
  *<b>Post: </b>The menu is displayed.<br>
  *@param millis Integer that describes the amount of milliseconds the console will wait per line. <b>Must be of type <i>int</i>.</b><br>
  *@param in Scanner object that receives user input. <b>Must be an already initialized <i>Scanner</i> object.</b><br>
  *@see #showMenu(int,Scanner) showMenu method
  */
  private void showAddPlaylistsMenu(int millis, Scanner in) {
    clear();
    int type = 0;
    int newUserIndex = 1;
    String newPlaylistName = "";
    System.out.println("************************************************");
    wait(millis);
    System.out.println("******************Nueva Playlist****************");
    wait(millis);
    in.nextLine();
    System.out.println("*Nombre de la playlist:                        *");
    newPlaylistName = in.nextLine();
    System.out.println(newPlaylistName);
    System.out.println("*Tipo de Playlist:                             *");
    slowPrint(millis, new String[] {
      "[1]Privada",
      "[2]Restringida",
      "[3]Publica"});
    type = in.nextInt();
    switch (type) {
      case 1:
        System.out.println("*****************Playlist Privada***************");
        wait(millis);
        System.out.println("*Creada por:                                   *");
        for (User u : msc.getUserList()) {
          System.out.println("**[" + newUserIndex + "] " + u.getUserName());
          newUserIndex++;
        }
        newUserIndex = in.nextInt() - 1;
        msc.addToPlaylist(newUserIndex,newPlaylistName);
        break;
      case 2:
        System.out.println("***************Playlist Restringida*************");
        wait(millis);
        System.out.println("*Usuario principal:                            *");
        for (User u : msc.getUserList()) {
          System.out.println("**[" + newUserIndex + "] " + u.getUserName());
          newUserIndex++;
        }
        newUserIndex = in.nextInt() - 1;
        msc.addToPlaylists(newPlaylistName,newUserIndex);
        break;
      case 3:
        msc.addToPlaylists(newPlaylistName);
        break;
      default:
        throw new IllegalStateException("Algo salio mal (" + type + " no es una seleccion valida)");
    }
    slowPrint(millis, new String[] {
      "************************************************",
      "************Playlist creada con exito!**********",
      "************************************************"
    });
    wait(1492);
  }

  /**
  *Called when the sixth option is selected in showMenu, allowing the app to show every playlist in<br>
  *the app with their current atributes.<br>
  *<b>Pre: </b> <br>
  *<b>Post: </b>The menu is displayed.<br>
  *@param millis Integer that describes the amount of milliseconds the console will wait per line. <b>Must be of type <i>int</i>.</b><br>
  *@param in Scanner object that receives user input. <b>Must be an already initialized <i>Scanner</i> object.</b><br>
  *@see #showMenu(int,Scanner) showMenu method
  */
  private void showPlaylistsMenu(int millis, Scanner in) {
    clear();
    String modPlayID;
    for (Playlist p : msc.getPlaylists()) {
      slowPrint(millis, p.showInfo());
    }
    slowPrint(millis, new String[] {
      "************************************************",
      "*Aniadir un nuevo rating                    [1]*",
      "*Aniadir un nuevo usuario                   [2]*",
      "*Volver                                     [0]*",
      "************************************************"});
    int answer = in.nextInt();
    in.nextLine();
    if (answer == 1) {
      System.out.println("**************Escoger una playlist**************");
      System.out.println("***Digitar el codigo exactamente como aparece***");
      for (Playlist p : msc.getPlaylists()) {
        if (p instanceof PublicPL) {
          System.out.println("**[" + p.getIdentity() + "] " + p.getPlaylistName());
        }
      }
      modPlayID = in.nextLine();
      System.out.println("*Puntaje (entre 1 y 5):                        *");
      double newScore = in.nextDouble();
      for (Playlist p : msc.getPlaylists()) {
        if (p.getIdentity().toUpperCase().equals(modPlayID.toUpperCase())) {
          PublicPL pl = (PublicPL)p;
          System.out.println(pl.modifyScore(newScore));
        }
      }
      wait(1500);
      showPlaylistsMenu(millis, in);
    }
    else if (answer == 2) {
      System.out.println("**************Escoger una playlist**************");
      System.out.println("***Digitar el codigo exactamente como aparece***");
      for (Playlist p : msc.getPlaylists()) {
        if (p instanceof RestrictedPL) {
          System.out.println("**[" + p.getIdentity() + "] " + p.getPlaylistName());
        }
      }
      modPlayID = in.nextLine();
      int i = 1;
      System.out.println("***************Escoger un usuario***************");
      for (User u : msc.getUserList()) {
        System.out.println("**[" + i + "] " + u.getUserName());
        i++;
      }
      int userAddedIndex = in.nextInt() - 1;
      for (Playlist p : msc.getPlaylists()) {
        if (p.getIdentity().equals(modPlayID)) {
          RestrictedPL rp = (RestrictedPL)p;
          rp.addUser();
        }
      }
      wait(1500);
      showPlaylistsMenu(millis, in);
    }
  }

  /**
  *Probes the console to wait for an given amount of milliseconds.<br>
  *<b>Pre: </b><br>
  *<b>Post: </b>If no exception is thrown, the system waits the specified amount of millis
  *@param millis Integer that specifies the amount of milliseconds for the console to wait. <b>Must be <i>an integer</i>.</b><br>
  */
  public static void wait(int millis) {
    try {
      Thread.sleep(millis);
    }catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
  *Clears the console.<br>
  *<b>Pre: </b>The system uses either CMD or Unix as their command line.<br>
  *<b>Post: </b>The console is cleared.<br>
  */
  public static void clear() {
    try {
      final String OS = System.getProperty("os.name");

      if (OS.contains("Windows")) {
        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
      }
      else {
        Runtime.getRuntime().exec("clear");
      }
    }catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
  *Loops through a string array and prints it with a given delay.<br>
  *<b>Pre: </b> <br>
  *<b>Post: </b>The given array is printed with the delay, applying the delay line by line.<br>
  *@param millis Integer that represents the delay in milliseconds. <b>Must be of type <i>int</i>.</b><br>
  *@param CONST String array containing the lines to be printed. <b>Must not be of length <i>0 or lower</i>.</b><br>
  */
  public static void slowPrint(int millis, String[] CONST) {
    for (String s : CONST) {
      System.out.println(s);
      wait(millis);
    }
  }
}
