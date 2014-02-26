var digicdc = {
    register: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'DigiCDC', // mapped to our native Java class called "DigiCDC"
            'register', // with this action name
            []
        ); 
    },
    red: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'DigiCDC', // mapped to our native Java class called "DigiCDC"
            'write', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "text": "1"
            }]
        ); 
    },
    black: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'DigiCDC', // mapped to our native Java class called "DigiCDC"
            'write', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "text": "0"
            }]
        ); 
    },
    green: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'DigiCDC', // mapped to our native Java class called "DigiCDC"
            'write', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "text": "2"
            }]
        ); 
    }

}
module.exports = LED;