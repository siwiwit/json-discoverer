/*******************************************************************************
 * Copyright (c) 2008, 2013
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Javier Canovas (javier.canovas@inria.fr) 
 *******************************************************************************/

package fr.inria.atlanmod.json.discoverer.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import fr.inria.atlanmod.discoverer.JsonComposer;
import fr.inria.atlanmod.discoverer.JsonSource;
import fr.inria.atlanmod.json.discoverer.zoo.ModelDrawer;

public class JsonComposerTest {

	public static String TEST_FILE_1 = "../fr.inria.atlanmod.json.discoverer.zoo/zoo/tan/source1/source1.ecore";
	public static String TEST_JSON_FILE_1 = "../fr.inria.atlanmod.json.discoverer.zoo/zoo/tan/source1/json1.json";
	public static String COVERAGE_FILE1 = "../fr.inria.atlanmod.json.discoverer.zoo/zoo/tan/source1/source1.coverage.xmi";

	public static String TEST_FILE_2 = "../fr.inria.atlanmod.json.discoverer.zoo/zoo/tan/source2/source2.ecore";
	public static String TEST_JSON_FILE_2 = "../fr.inria.atlanmod.json.discoverer.zoo/zoo/tan/source2/json1.json";
	public static String COVERAGE_FILE2 = "../fr.inria.atlanmod.json.discoverer.zoo/zoo/tan/source2/source2.coverage.xmi";

	public static String TEST_FILE_3 = "../fr.inria.atlanmod.json.discoverer.zoo/zoo/tan/source3/source3.ecore";
	public static String TEST_JSON_FILE_3 = "../fr.inria.atlanmod.json.discoverer.zoo/zoo/tan/source3/json1.json";
	public static String COVERAGE_FILE3 = "../fr.inria.atlanmod.json.discoverer.zoo/zoo/tan/source3/source3.coverage.xmi";
	
	public static String RESULT = "../fr.inria.atlanmod.json.discoverer.zoo/zoo/tan/tan.ecore";

	public static void main(String[] args) throws FileNotFoundException  {

		JsonSource source1 = new JsonSource("stopPosition");
		source1.addJsonDef(new File(TEST_JSON_FILE_1));
		
		JsonSource source2 = new JsonSource("waitingTimeStop");
		source2.addJsonDef(new File(TEST_JSON_FILE_2));

		JsonSource source3 = new JsonSource("horariesStopLineDirection");
		source3.addJsonDef(new File(TEST_JSON_FILE_3));
				
		List<File> coveragePaths = new ArrayList<File>();
		coveragePaths.add(new File(COVERAGE_FILE1));
		coveragePaths.add(new File(COVERAGE_FILE2));
		coveragePaths.add(new File(COVERAGE_FILE3));

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		
		List<JsonSource> sources = new ArrayList<JsonSource>();
		sources.add(source1);
		sources.add(source2);
		sources.add(source3);
		
		JsonComposer composer = new JsonComposer(sources);
		composer.compose("composed", new File(RESULT));
		composer.saveCoverage(coveragePaths);

		ModelDrawer drawer = new ModelDrawer(
				new File("C:/Users/useradm/git/json-discoverer/fr.inria.atlanmod.json.discoverer.zoo/workingDir"), 
				new File("C:/Program Files (x86)/Graphviz 2.28/bin/dot.exe"));
		drawer.traverseAndDrawFolder(new File("../fr.inria.atlanmod.json.discoverer.zoo/zoo/tan"));
	}
}
