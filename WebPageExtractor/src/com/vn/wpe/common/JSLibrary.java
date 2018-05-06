package com.vn.wpe.common;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSLibrary {

	public static enum WIKIPEDIA {

		/**
		 * DOM (manipulation) oriented
		 */
		
		JQUERY("jquery", "pep"),
		DOJO("dojo"),
		MIDORI("midori"),
		MOOTOOLS("mootool"),
		PROTOTYPE("protoype"),
		
		/**
		 * Graphical/visualization (canvas or SVG related)
		 */
		
		ANY_CHART("anychart", "anystock", "anymap", "anygantt"),
		D3("d3"),
		FUSION_CHARTS("fusion"),
		CREATE_JS("easeljs", "tweenjs", "soundjs", "preloadjs", "zoe"),
		P5("p5"),
		PROCESSING("processing"),
		SWF_OBJECT("swf"),
		THREE("three"),
		VELOCITY("velocity"),
		WHITESTORM("wht"),
		
		/**
		 * GUI-related (widget)
		 */
		
		BOOTSTRAP("bootstrap"),
		EXT_JS("extjs"),
		JQ_WIDGETS("jqwidget", "jqx"),
		OPENUI5("openui"),
		QOOXDOO("qooxdoo"),
		SMART_CIENT("smartclient"),
		REACT_JS("react"),
		WEBIX("webix"),
		WIN_JS("winjs"),
		
		/**
		 * Pure JavaScript/Ajax
		 */
		
		CLOSURE("closure"),
		JOOSE("joose"),
		JS_PHP("jsphp"),
		MOCHI_KIT("mochi"),
		PDF("pdf"),
		RICO("rico"),
		SOCKET_IO("socket"),
		SPRY_FRAMEWORK("spry"),
		UNDERSCORE("underscore"),
		MUSTACHE("mustache"),
		JINJA("jinja"),
		TWIG("twig"),
		
		/**
		 * Unit testing
		 */
		
		JASMINE("jasmine"),
		MOCHA_JS("mmocha"),
		QUNIT("qunit"),
		TAPE("tape"),
		UNIT_JS("unitjs"),
		
		/**
		 * Web-application related (MVC, MVVM)
		 */
		
		ANGULAR_JS("angular"),
		BACKBONE("backbone"),
		CAPPUCCION("cappuccion"),
		CHAPLIN("chaplin"),
		ECHO("echo"),
		EMBER("ember"),
		ENYO("enyo"),
		GWT("gwt"),
		KNOCKOUT("knockout"),
		METEOR("meteor"),
		MOJITIO("mojitio"),
		NODE("node"),
		SPROUT_CORE("sprout"),
		VUE("vue"),
		CANNON("cannon");
		
		
		private String[] patterns;

		WIKIPEDIA(String... patterns) {
			this.patterns = patterns;
		}

		public String[] getPatterns() {
			return patterns;
		}

		public static List<String> getAll() {
			return (List<String>) Stream.of(WIKIPEDIA.values())
					.map(a -> a.name())
					.collect(Collectors.toList());
		}
		
	}
}
