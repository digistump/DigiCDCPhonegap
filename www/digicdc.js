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
    red: function() {
        cordova.exec(
            function(){}, // success callback function
            function(){}, // error callback function
            'Digicdc', // mapped to our native Java class called "DigiCDC"
            'write', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "text": "1"
            }]
        ); 
    },
    black: function() {
        cordova.exec(
            function(){}, // success callback function
            function(){}, // error callback function
            'Digicdc', // mapped to our native Java class called "DigiCDC"
            'write', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "text": "0"
            }]
        ); 
    },
    green: function() {
        cordova.exec(
            function(){}, // success callback function
            function(){}, // error callback function
            'Digicdc', // mapped to our native Java class called "DigiCDC"
            'write', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "text": "2"
            }]
        ); 
    },
    wifi: function() {
        cordova.exec(
            function(){}, // success callback function
            function(){}, // error callback function
            'Digicdc', // mapped to our native Java class called "DigiCDC"
            'wifi', // with this action name
            []
        ); 
    },
    hide: function() {
        cordova.exec(
            function(){}, // success callback function
            function(){}, // error callback function
            'Digicdc', // mapped to our native Java class called "DigiCDC"
            'hide', // with this action name
            []
        ); 
    },
    show: function() {
        cordova.exec(
            function(){}, // success callback function
            function(){}, // error callback function
            'Digicdc', // mapped to our native Java class called "DigiCDC"
            'show', // with this action name
            []
        ); 
    }

}
module.exports = digicdc;