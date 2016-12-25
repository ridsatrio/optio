/*
 * Copyright (C) 2015 Ridho Hadi Satrio
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package id.ridsatrio.optio.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import id.ridsatrio.optio.Optional;

public class DemoActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_demo);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

        //Creating empty optional
        Optional<String> testString = Optional.empty();

        // Get value or placeholder, if optional is empty
        final String stringValue = testString.orElse("String is null!");
        Snackbar.make(view, stringValue, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();

        //Creating optional with value
        testString = Optional.of("Now the string is not null!");
        testString.isPresent(); //true

        final String newStringValue = testString.orElse("String is null!");

        //Make toast if we have value in optional
        testString.ifPresent(new Optional.Consumer<String>() {
          @Override public void accept(String s) {
            Toast.makeText(DemoActivity.this, newStringValue, Toast.LENGTH_SHORT).show();
          }
        });

        //You can use retrolambda to emulate Java 8's lambdas
        //Then ifPresent will be look like:

        //testString.ifPresent(s -> Toast.makeText(DemoActivity.this, s, Toast.LENGTH_SHORT).show());

      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_demo, menu);

    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
