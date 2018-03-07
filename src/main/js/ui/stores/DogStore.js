var EventEmitter = require("events").EventEmitter;
var ActionTypes = require('../constants/AppConstants').ActionTypes;
import AppDispatcher from "../dispatcher/AppDispatcher";

var DogStore = Object.assign({}, EventEmitter.prototype, {
	dogList: function(data) {
		this.emitChange(data);
	},

	login: function(data) {
		this.emitLogin(data);
	},

	emitChange: function(param) {
		this.emit("change", param);
	},

	emitLogin: function(param) {
		this.emit("login", param);
	},

	addChangeListener: function(callback) {
		this.on("change", callback);
	},

	addLoginListener: function(callback) {
		this.on("login", callback);
	},

	removeLoginListener: function(callback) {
		this.removeListener("login", callback);
	},

	removeChangeListener: function(callback) {
		this.removeListener("change", callback);
	},

	dispatchToken: AppDispatcher.register((payload) => {
		switch(payload.type) {
			case ActionTypes.DOG_LIST:
				DogStore.dogList(payload.data);
				break;
			case ActionTypes.LOGIN:
				DogStore.login(payload.data);
				break;
		}
		return true;
	})
});

module.exports = DogStore;