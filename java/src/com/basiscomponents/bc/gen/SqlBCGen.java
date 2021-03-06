package com.basiscomponents.bc.gen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SqlBCGen {

	private static String Tpl;

	public static void gen(String Driver, String Url, String User, String Password, String packageName, String outputDir) throws FileNotFoundException {
		if (Tpl == null){
			SqlBCGen.loadTpl(SqlBCGen.class.getResourceAsStream("/com/basiscomponents/bc/gen/TableBC.txt"));
		}

        try {
        	Class.forName(Driver);
        	Connection conn = DriverManager.getConnection(Url,User,Password);
            DatabaseMetaData dbmd = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, "%", types);
            while (rs.next()) {
            	SqlBCGen.genClass(rs.getString("TABLE_NAME"), packageName, outputDir);
            }
        }
            catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
	}

	public static void gen(String Driver, String Url, String User, String Password, String packageName, String outputDir, String templatePath) throws FileNotFoundException {
		SqlBCGen.loadTpl(new java.io.FileInputStream(templatePath));
		gen(Driver, Url, User, Password, packageName, outputDir);
	}

	private static void loadTpl(InputStream is) throws FileNotFoundException {
		final char[] buffer = new char[1024];
	    final StringBuilder out = new StringBuilder();
	    final BufferedReader br = new BufferedReader(new InputStreamReader(is));

		try {
			int rsz;
			while ((rsz=br.read(buffer)) != -1) {
				out.append(buffer, 0, rsz);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    SqlBCGen.Tpl = out.toString();
	}


	private static void genClass(String tableName, String packageName, String outputDir) throws FileNotFoundException {
		System.out.println("generating: "+tableName);
		String tmp = SqlBCGen.Tpl;

		String className = SqlBCGen.toCamelCase(tableName)+"BC";

		tmp=tmp.replace("[[class]]", className);
		tmp=tmp.replace("[[package]]", packageName);
		tmp=tmp.replace("[[table]]", tableName);

		PrintWriter out = new PrintWriter(outputDir+className+".java");
		out.println(tmp);
		out.close();
	}

	private static String toCamelCase(String tableName) {
		return tableName.substring(0, 1).toUpperCase()+tableName.substring(1).toLowerCase();
	}

}
