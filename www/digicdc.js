var digicdc = {
    register: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Digicdc', // mapped to our native Java class called "DigiCDC"
            'register', // with this action name
            []
        ); 
    },
    write: function(data) {
        cordova.exec(
            function(){}, // success callback function
            function(){}, // error callback function
            'Digicdc', // mapped to our native Java class called "DigiCDC"
            'write', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "text": data
            }]
        ); 
    }
}
module.exports = digicdc;