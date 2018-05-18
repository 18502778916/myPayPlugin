#!/usr/bin/env node

module.exports = function (context) {
     var path         = context.requireCordovaModule('path'),  
        fs           = context.requireCordovaModule('fs'),  
        shell        = context.requireCordovaModule('shelljs'),  
        projectRoot  = context.opts.projectRoot,  
        ConfigParser = context.requireCordovaModule('cordova-common').ConfigParser,  
        config       = new ConfigParser(path.join(context.opts.projectRoot, "config.xml")),  
        packageName = config.android_packageName() || config.packageName();  

    console.info("Running android-install.Hook: " + context.hook + ", Package: " + packageName + ", Path: " + projectRoot + ".");

    if (!packageName) {
        console.error("Package name could not be found!");
        return ;
    }
    
    var targetDir  = path.join(projectRoot, "platforms", "android", "src","main","java", "com.jinan.firsttest".replace(/\./g, path.sep));
        targetFile = path.join(targetDir, "TestActivity.java");

    if (['after_plugin_add', 'after_plugin_install', 'after_platform_add'].indexOf(context.hook) === -1) {
        // remove it?
        try {
            fs.unlinkSync(targetFile);
        } catch (err) {}
    } else {
        // sync the content
        fs.readFile(path.join(context.opts.plugin.dir, 'src', 'android', 'TestActivity.java'), {encoding: 'utf-8'}, function (err, data) {
            if (err) {
                throw err;
            }

            data = data.replace(/^import _PACKAGE_NAME_;/m, 'import ' + packageName + '.R;');
            fs.writeFileSync(targetFile, data);
        });
    }
};