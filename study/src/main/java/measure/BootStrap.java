package measure;

import measure.server.Server;

public class BootStrap {

	public static void main(String[] args) {

		Server server = new Server();
		server.run();

	}

}
