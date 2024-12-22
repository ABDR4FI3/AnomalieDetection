import axios from "axios";

const BASE_URL = "http://127.0.0.1:5000";

export const fetchCrimeData = async (page: number = 1 , limit: number = 1000) => {
  try {
    console.log(`${BASE_URL}/crime_data?page=${page}&page_size=${limit}`);
    const response = await axios.get(`${BASE_URL}/crime_data?page=${page}&page_size=${limit}`);
    // console.log(response.data.data);
    return response.data.data; // Return the array of crimes
  } catch (error) {
    console.error("Error fetching crime data:", error);
    return [];
  }
};
