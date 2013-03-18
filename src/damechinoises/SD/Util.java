package damechinoises.SD;


import java.io.File;

public class Util
{
  private static File workDir = null;

  public static File getWorkingDirectory() {
    if (workDir == null) workDir = getWorkingDirectory("DamesChinoises");
    return workDir;
  }

   public static File getWorkingDirectory(String applicationName) {
   String userHome = System.getProperty("user.home", ".");
   File workingDirectory;
   if(getPlatform() == OS.solaris || getPlatform() ==  OS.linux)
   {
     workingDirectory = new File(userHome, '.' + applicationName + '/');
   }
   else if(getPlatform() == OS.windows)
   {
     String applicationData = System.getenv("APPDATA");
     if (applicationData != null) workingDirectory = new File(applicationData, "." + applicationName + '/'); else
       workingDirectory = new File(userHome, '.' + applicationName + '/');
   }
   else if(getPlatform() == OS.macos)
   {
     workingDirectory = new File(userHome, "Library/Application Support/" + applicationName + '/');
   }
   else
   {
     workingDirectory = new File(userHome, applicationName + '/');
   }
   
   if ((!workingDirectory.exists()) && (!workingDirectory.mkdirs())) throw new RuntimeException("Le répertoire de travail n'a pas pu être créé: " + workingDirectory);
   File saveDir = new File(workingDirectory, "Sauvegardes/");
   File platDir = new File(workingDirectory, "Plateaux/");
   if ((!saveDir.exists()) && (!saveDir.mkdirs())) throw new RuntimeException("Le répertoire de travail n'a pas pu être créé: " + workingDirectory);
   if ((!platDir.exists()) && (!platDir.mkdirs())) throw new RuntimeException("Le répertoire de travail n'a pas pu être créé: " + workingDirectory);
   return workingDirectory;
 }

  private static OS getPlatform() {
    String osName = System.getProperty("os.name").toLowerCase();
    if (osName.contains("win")) return OS.windows;
    if (osName.contains("mac")) return OS.macos;
    if (osName.contains("solaris")) return OS.solaris;
    if (osName.contains("sunos")) return OS.solaris;
    if (osName.contains("linux")) return OS.linux;
    if (osName.contains("unix")) return OS.linux;
    return OS.unknown;
  }

  public static boolean isEmpty(String str) {
    return (str == null) || (str.length() == 0);
  }

  private static enum OS
  {
    linux, solaris, windows, macos, unknown;
  }
}
