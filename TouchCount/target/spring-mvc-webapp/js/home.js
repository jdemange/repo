
$(document).ready(function () {
    displayNewOrExistingAudit();



});

////LOGIN/////
//***
function loadLoginPage() { ////THIS WILL NEED FIXED TO BE ACTUALLY IMPLEMENTED
    $('.room-details').hide();
    $('.current-code-items').hide();

    $.ajax({
        type: 'GET',
        url: 'userController/displayLoginPage',

        success: function (data, status) {
            $('div.choices').replaceWith(data);
        },
        error: function () {
            alert("oh no!");
        }

    })
}
//***
function login() {

    var user = $('#userName').val();
    var password = $('#password').val();

    $('#current-user').append(user);

    $.ajax({
        type: 'GET',
        url: 'auditController/displayNewOrExistingAudit',

        success: function (data, status) {
            $('div.choices').replaceWith(data);
        },
        error: function () {
            alert("oh no!");
        }

    });


}
;
function verify() {
    var code1 = $('#promo-code').val();

    $.ajax({
        type: 'POST',
        url: 'openTheApp',
        dataType: 'json',
        data: JSON.stringify({
            code: code1
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        statusCode: {

            500: function () {
                alert('Looks like you need the code. Email jocelyn@touchcount.lighting for more informtion.');
            },
            200: function () {
                window.location = 'reallyOpenTheApp';
            }}

    });
}
///**
function displayNewOrExistingAudit() {
    $('.room-details').hide();
    $('.current-code-items').hide();

    $.ajax({
        type: 'GET',
        url: 'auditController/displayNewOrExistingAudit',

        success: function (data, status) {
            $('div.choices').replaceWith(data);
        },
        error: function () {
            alert("oh no!");
        }

    });
}

//INITIAL AUDIT QUESTIONS

//***
function displayNewAuditForm() {
    $.ajax({
        type: 'GET',
        url: 'auditController/displayNewAuditForm',

        success: function (data, status) {
            $('div.choices').replaceWith(data);
        },
        error: function () {
            alert("oh no!");
        }

    })
}
;
function continueAudit(auditId){
    $('#audit-id').attr('value', auditId);

    loadInitialFixtureOptions();
}

//***
function createAudit() {
//    var password = $('#location').val();
//     if (!password){
//  myApp.alert('Please fill in all Registration form fields');
//  return;
// }
    $.ajax({
        type: 'POST',
        url: 'auditController/createAudit',
        processData: false,
        dataType: 'json',
        data: JSON.stringify({
            locName: $('#location').val(),
            userId: $('#USER').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        statusCode: {
            200: success200
        }
    });
}
//***
var success200 = function (data) {
    var auditId = data.auditId;
    $('#audit-id').attr('value', auditId);

    loadInitialFixtureOptions();

};

function listAudits() {
    var user = $('#USER').val();
    $('.room-details').hide();
    $('.current-code-items').hide();

    $.ajax({
        type: 'GET',
        url: 'auditController/displayAuditList/' + user,

        success: function (data, status) {
            $('div.choices').replaceWith(data);
        },
        error: function () {
            alert("oh no!");
        }

    });
}
;
///**THIS SHOWS AUDIT LINES
function auditDetails(auditNumber) {
    $('div.choices').replaceWith(auditNumber);
}
;

function deleteAudit(auditId) {
    $.ajax({
        type: 'GET',
        url: 'auditController/deleteAudit/' + auditId,

        success: function (data, status) {
            listAudits();
        },
        error: function () {
            alert("oh no!");
        }

    });
}

function displayAuditLines() {
    $('.room-details').hide();
    $('.current-code-items').hide();
    var auditNumber = $('#audit-id').val();

    $.ajax({
        type: 'GET',
        url: 'auditController/displayAuditLines/' + auditNumber,

        success: function (data, status) {
            $('div.choices').replaceWith(data);
        },
        error: function () {
            alert("oh no!");
        }

    });
}
;

function displayAuditLinesFromAuditList(auditId) {
    $('.room-details').hide();
    $('.current-code-items').hide();
    $.ajax({
        type: 'GET',
        url: 'auditController/displayAuditLines/' + auditId,

        success: function (data, status) {
            $('div.choices').replaceWith(data);
        },
        error: function () {
            alert("oh no!");
        }

    });
}
function deleteEntryItem(entryId) {
    $.ajax({
        type: 'GET',
        url: 'auditController/deleteEntry/' + entryId,

        success: function (data, status) {
            $('div.choices').replaceWith(data);
        },
        error: function () {
            alert("oh no!");
        }

    });
}


function loadInitialFixtureOptions() {
    $('.room-details').show();
    $('.current-code-items').show();

    $('#base-code').val("");
    $('#extended-code').val("");

    $.ajax({
        type: 'GET',
        url: 'optionsController/initialChoice',

        success: function (data, status) {
            $('div.choices').replaceWith(data);
        },
        error: function () {
            alert("oh no!");
        }

    });
}
function displayEntrySummary() {
    $.ajax({
        type: 'GET',
        url: 'optionsController/displayEntrySummary',

        success: function (data, status) {
            $('div.choices').replaceWith(data);
        },
        error: function () {
            alert("oh no!");
        }

    });
}




////WHEN A SELECTION IS MADE////    
function addToCode(objButton) {
    var newUrl = objButton.name;
    nextPage(newUrl);
    var val1 = $('#base-code').val();
    var val2 = objButton.value;
    $('#base-code').val(val1 + val2);
}
;

function addToCodeOnly(objButton) {
    var val1 = $('#extended-code').val();
    var val2 = objButton.value;
    $('#extended-code').val(val1 + val2);
}

function nextPageNoCode(objButton) {
    var newUrl = objButton.name;
    nextPage(newUrl);
}
;

function nextPage(newUrl) {
    $.ajax({
        type: 'GET',
        url: newUrl,

        success: function (data, status) {
            $('div.choices').replaceWith(data);
        },
        error: function () {
            alert("oh no!");
        }

    });
}



function loadOptions() {
    $.ajax({
        type: 'GET',
        url: 'initialOptions',

        success: function (data, status) {
            $('#main').append(data);
        },
        error: function () {
            alert("oh no!");
        }

    });
}

function addEntry() {

    $.ajax({
        type: 'POST',
        url: 'auditController/addEntry',
        processData: false,
        dataType: 'json',
        data: JSON.stringify({
            auditId: $('#audit-id').val(),
            mapNumber: $('#map-number').val(),
            roomName: $('#room-description').val(),
            floorNumber: $('#floor-number').val(),
            baseCode: $('#base-code').val(),
            extendedCode: $('#extended-code').val(),
            quantity: $('#quantity').val(),
            comments: $('#comments').val(),
            fixtureHeight: $('#fixture-height').val(),
            ceilingHeight: $('#ceiling-height').val(),
            roomType: $('#room-code').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        statusCode: {
            200: code200b
        }



    });
}

var code200b = function newLine() {
    loadInitialFixtureOptions();
};


