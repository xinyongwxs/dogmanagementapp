import AppDispatcher from "../dispatcher/AppDispatcher";
import AppConstants from "../constants/AppConstants";
import DogService from "../services/DogService";

const ActionTypes = AppConstants.ActionTypes;

export default {
	dogList: (dogType) => {
		return DogService.getDogList(dogType).then((data) => {
			AppDispatcher.dispatch({
				type: ActionTypes.DOG_LIST,
				data: data
			});
		});
	}
}