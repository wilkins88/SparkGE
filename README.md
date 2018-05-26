# SparkGE

SparkGE is a fun side project to explore building both a simple game as well as the underlying game engine

### Prerequisites

* [Git] (https://git-scm.com/)
* [Java JDK] (http://www.oracle.com/technetwork/java/javase/downloads/)

### Setup using Intellij Idea

Boot up Intellij Idea. At the select screen, choose Checkout from Version Control, and choose git. Enter the url for
this repository, and continue.

Once the project has been created, verify that the source folder has been identified (it should be blue). If it is not,
right click the src folder, navigate to "Mark Directory As" => "Sources Root."

Next, link the lwjgl libraries in the libs folder by navigating to "File" => "Project Structure" => "Libraries." Hit the
"Add" button (the green plus sign), select Java, and select the libs folder in your project directory. Once the folder
has been selected, click apply, and then ok when the indexing is done.

Finally, to configure run navigate to "Run" => "Edit Configurations." Click the "Add" button and select "Application."
Name the configuration whatever you like, and set the Main class to "com.sparkge.core.Main."

To verify that everything has been set up correctly, run the new configuration and verify that a window pops up.

## Built With

* [LWJGL](https://www.lwjgl.org/) - Java game development framework
