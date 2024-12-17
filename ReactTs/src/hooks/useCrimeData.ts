import { useEffect, useState } from "react";
import { fetchCrimeData } from "../Services/crimeService";
import { CrimeData } from "../types/crimeData";



const useCrimeData = (page: number = 1) => {
  const [data, setData] = useState<CrimeData[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const loadData = async () => {
      setLoading(true);
      const fetchedData = await fetchCrimeData(page);
      const mappedData = fetchedData.map((item: any) => ({
        description: item["Crm Cd Desc"],
        date: item["DATE OCC"],
        lat: item["LAT"],
        lon: item["LON"],
      }));
      // console.log("mappedData inside the hook", mappedData);
      setData(mappedData);
      setLoading(false);
    };

    loadData();
  }, [page]);

  return { data, loading };
};

export default useCrimeData;
