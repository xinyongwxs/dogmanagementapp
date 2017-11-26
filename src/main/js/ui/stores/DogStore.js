var EventEmitter = require("events").EventEmitter;
var ActionTypes = require('../constants/AppConstants').ActionTypes;
import AppDispatcher from "../dispatcher/AppDispatcher";

var DogStore = Object.assign({}, EventEmitter.prototype, {
	dogList: function(data) {
		this.emitChange(data);
	},

	emitChange: function(param) {
		this.emit("change", param);
	},

	addChangeListener: function(callback) {
		this.on("change", callback);
	},

	removeChangeListener: function(callback) {
		this.removeListener("change", callback);
	},

	dispatchToken: AppDispatcher.register((payload) => {
		switch(payload.type) {
			case ActionTypes.DOG_LIST:
				DogStore.dogList(payload.data);
				break;
		}
		return true;
	})
});

module.exports = DogStore;