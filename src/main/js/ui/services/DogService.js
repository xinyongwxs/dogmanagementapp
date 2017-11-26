import client from './client';

export default {
	getDogList: (dogType) => {
		let path = "";
		if (dogType === "BreedingDog") {
			path = "/breedingdog";
		} else if (dogType === "PetDog") {
			path = "/petdog";
		} else if (dogType === "WorkingDog") {
			path = "/workingdog";
		} else if (dogType === "TrainingDog") {
			path = "/trainingdog";
		}

		let promise = client({
			method: "GET",
			path: path
		});

		return promise;
	}
}