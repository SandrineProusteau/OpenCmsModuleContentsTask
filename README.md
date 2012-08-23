OpenCmsModuleContentsTask
=========================

Tache ant pour les projets OpenCms 8 dans Eclipse



=== 23 Aout 2012 ===
Dans le build.xml du projet Eclipse, verifier que les variables suivantes sont definies :
	${folder.complib}	= etc/complib
	${folder.vfs}		= vfs_module
	${file.resourcetypes}	= etc/resourcetypes.xml
	${file.explorertypes}	= etc/explorertypes.xml
	${jdk.version}		= 1.7
	${module.name}
	${module.nicename}
	${module.group}
	${module.class}
	${module.description}
	${module.version}
	${module.authorname}
	${module.authoremail}
	${module.opencmsversion}
	${module.exportversion}
	${folder.exportto}	= contents (chemin du dossier d'export du fichier de contenus du module)

Placer le jar dans ${folder.complib} du projet Eclipse.

Ajouter la tache suivante dans le build.xml du projet Eclipse:

	<!-- CONSTRUCTION DU FICHIER DE RECAPITULATIF DES CONTENUS -->
	<taskdef name="contents" classname="OpenCmsModuleContentsTask">
		<classpath>
			<fileset dir="${folder.complib}" includes="*.jar" />
		</classpath>
	</taskdef>	
	<target name="generate-module-contents">
		<mkdir dir="${folder.exportto}" />
		<contents 
			modulename="${module.name}"
			modulenicename="${module.nicename}"
			modulegroup="${module.group}"
	      	moduleclass="${module.class}"
	      	moduledescription="${module.description}"
	      	moduleversion="${module.version}"
	      	moduleauthorname="${module.authorname}"
	      	moduleauthoremail="${module.authoremail}"
			opencmsversion="${module.opencmsversion}"
			exportversion="${module.exportversion}"
			jdkversion="${jdk.version}"
				
			resourcetypes="${file.resourcetypes}"
			explorertypes="${file.explorertypes}"
			vfs="${folder.vfs}"
			exportto="${folder.exportto}"
		/>
	</target>