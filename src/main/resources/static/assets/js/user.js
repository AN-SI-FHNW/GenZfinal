function getAllUsers(callback) {
	$.ajax({
		type : "GET",
		dataType : "json",
		url : serviceEndpointURL + "/api/user",
		success : function(data) {
			callback(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
		}
	});
}

function deleteUser(userId, callback) {
	$.ajax({
		type : "DELETE",
		headers : {
			"X-XSRF-TOKEN" : getCookie("XSRF-TOKEN")
		},
		url : serviceEndpointURL + "/api/user/" + userId,
		success : function(data) {
			callback(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
		}
	});
}