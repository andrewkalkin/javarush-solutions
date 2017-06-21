package com.javarush.task.task27.task2712.ad;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kalinnikov_al on 18.04.2017.
 */
public class AdvertisementManager {

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    private class videoPair {
        long profit;
        int sumTime;
        List<Advertisement> videos;

        public videoPair(long profit, int sumTime, List<Advertisement> videos) {
            this.profit = profit;
            this.videos = videos;
            this.sumTime = sumTime;
        }
    }


    public void processVideos(){

        List<Advertisement> searchList = new ArrayList<>();

        for (Advertisement advertisement: storage.list()) {
            if (advertisement.getHits() > 0) searchList.add(advertisement);
        }

        if (searchList.isEmpty()) throw new NoVideoAvailableException();


        videoPair bestVideos = getBestVideos(searchList, new ArrayList<>(), timeSeconds, 0, 0);

        Collections.sort(bestVideos.videos, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                if (o2.getAmountPerOneDisplaying() == o1.getAmountPerOneDisplaying()) return (int) ((long) o1.getAmountPerOneDisplaying()*1000/o1.getDuration() - (long) o2.getAmountPerOneDisplaying()*1000/o2.getDuration());
                return (int)(o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
            }
        });

        if (bestVideos.videos.isEmpty() || bestVideos.videos == null) throw new NoVideoAvailableException();

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(bestVideos.videos, bestVideos.profit, bestVideos.sumTime));

        for (Advertisement video: bestVideos.videos){
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", video.getName(), video.getAmountPerOneDisplaying(), (long) video.getAmountPerOneDisplaying()*1000/video.getDuration()));
            video.revalidate();
        }


    }

    private videoPair getBestVideos(List<Advertisement> availableVideos, List<Advertisement> listVideos, int totalTime, long totalProfit, int sumTime){
        if (availableVideos.isEmpty()) return new videoPair(totalProfit, sumTime, listVideos);

        List<Advertisement> tempAvailableVideos = new ArrayList<>();
        tempAvailableVideos.addAll(availableVideos);

        for (Advertisement advertisement: availableVideos) {
            if (advertisement.getDuration() > totalTime) tempAvailableVideos.remove(advertisement);
        }

        if (tempAvailableVideos.isEmpty()) return new videoPair(totalProfit, sumTime, listVideos);

        Advertisement adv = tempAvailableVideos.get(0);

        tempAvailableVideos.remove(adv);

        List<Advertisement> tempListVideos = new ArrayList<>();
        tempListVideos.addAll(listVideos);
        tempListVideos.add(adv);

        videoPair pair1 = getBestVideos(tempAvailableVideos, listVideos, totalTime, totalProfit, sumTime);
        videoPair pair2 = getBestVideos(tempAvailableVideos, tempListVideos, totalTime-adv.getDuration(), totalProfit+adv.getAmountPerOneDisplaying(), sumTime + adv.getDuration());

        if (pair1.profit > pair2.profit) return pair1;
        if (pair1.profit < pair2.profit) return pair2;

        if (pair1.sumTime > pair2.sumTime) return pair1;
        if (pair1.sumTime < pair2.sumTime) return pair2;

        return pair1.videos.size() > pair2.videos.size() ? pair2 : pair1;


    }


}
