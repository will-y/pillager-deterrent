package dev.willyelton.pillagerdeterrent.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Sync;

@Config(name = "pillager-deterrent", wrapperName = "PillagerDeterrentConfig")
@Sync(Option.SyncMode.OVERRIDE_CLIENT)
public class PillagerDeterrentConfigModel {
    public int bannerRange = 128;
}
