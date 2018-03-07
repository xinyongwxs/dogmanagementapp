import keyMirror from 'keymirror';

export const ActionTypes = keyMirror({
	DOG_LIST: null,
	LOGIN: null
});

export const DeviceTypes = keyMirror({
	BROWSER_CHROME: null
});

export default {ActionTypes, DeviceTypes};